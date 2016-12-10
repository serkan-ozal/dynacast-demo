1. What is this demo?
==============
**DynaCast Demo** is a demo application which shows how to use [DynaCast](https://github.com/serkan-ozal/dynacast). The demo application has *client-server* architecture as explained [here](https://github.com/serkan-ozal/dynacast/blob/master/README.md#5-client-server-architecture). 

2. Configuration
==============

2.1. AWS Credentials
--------------
* **`aws.accessKey:`** Your AWS access key
* **`aws.secretKey:`** Your AWS secret key

These properties can be specified as system property or can be given from **`aws-credentials.properties`** configuration file under `src/main/resources` directory.

2.2. DynaCast Configurations
--------------
There are `dynacast.properties` file under `src/main/resources` directory. You can configure the properties as explained [here](https://github.com/serkan-ozal/dynacast#32-dynacast-configurations).

3. Installation & Deployment & Run
==============
Since the demo application has *client-server* architecture, you need to deploy **DynaCast** server nodes into the AWS to form a cluster.

You can build and deploy the **DynaCast** server application into the AWS via maven by `mvn -Pawseb clean package deploy` (or just run `build-and-deploy-server.sh`/`build-and-deploy-server.bat`). So the server application is packaged with its dependencies, uploaded to **AWS S3**, **AWS EC2** instance is provisioned and deployed onto it via **AWS Elastic Beanstalk**. Then it **DynaCast** cluster becomes up. For terminating the cluster, you can run `mvn -Pawseb beanstalk:terminate-environment` by maven or execute `terminater-server.sh`/`terminater-server.bat` script.

There are some prerequisites for the deployment via maven:
* AWS account id as value of `<aws.user.accountId>` tag under `properties` tag in the `pom.xml` must be filled.
* Due to usage of **AWS Elastic Beanstalk**'s maven [plugin](http://beanstalker.ingenieux.com.br/beanstalk-maven-plugin/usage.html), there must be `~/.aws/credentials` file that contains your AWS access and secret keys in the following format:

```
[default]
aws_access_key_id=XYZ123...
aws_secret_access_key=ABC567...
```

There are also some configurable properties in the `pom.xml`. Here are some of remarkable ones:
* **`beanstalk.instanceType:`** Specifies instance type of the **AWS EC2** machine where servers run. See [here](https://aws.amazon.com/ec2/instance-types) for more details about **AWS EC2** instance types and [here](http://beanstalker.ingenieux.com.br/beanstalk-maven-plugin/put-environment-mojo.html) for more configurations.
* **`beanstalk.keyName:`** Specifies **AWS EC2** key pair for connecting to the **AWS EC2** machines where servers run.
* **`beanstalk.environmentType:`** Specifies whether server(s) will run as a single instance or as a cluster behind the load balancer on multiple **AWS EC2** instances with auto scaling options. By default it is in the cluster mode with minimum 1 node and maximum 4 nodes auto-scaling configuration. If you want only a single node for server, you can set this property to `Single Instance`.
* **`aws:autoscaling.???:`** Specifies what is the limits of the cluster (minimum and maximum instance count in the cluster) for scaling up/down and in which conditions cluster will scale up/down. By default, auto-scaling triggered according to outgoing network usage.

When **DynaCast** cluster is up and running (can be verified via **AWS Elastic Beanstalk** console), you can run [DynaCastDemo](https://github.com/serkan-ozal/dynacast-demo/blob/master/src/main/java/tr/com/serkanozal/dynacast/demo/DynaCastDemo.java) application which runs in client mode and connects to the cluster you have created above to perform data accesses/mutations.

