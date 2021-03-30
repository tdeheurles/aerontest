# EKS CLUSTER WITH ATYOS RUNNER

Simple management of an EKS cluster using the AtyosRunner

## Prerequisites
- set your `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, `AWS_REGION` and `PROJECT_NAME` 
  by copying `do.secret.env.tmpl` to `do.secret.env` and setting them inside

## Start the cluster
```shell
./do --component="eks_cluster" --command="cluster_create" --cluster_name="dev"
```
After around 15 minutes, the output should be something like that:
```shell
Creating Cluster
2021-03-30 07:14:17 [ℹ]  eksctl version 0.42.0
2021-03-30 07:14:17 [ℹ]  using region eu-west-3
2021-03-30 07:14:17 [ℹ]  setting availability zones to [eu-west-3b eu-west-3a eu-west-3c]
2021-03-30 07:14:17 [ℹ]  subnets for eu-west-3b - public:192.168.0.0/19 private:192.168.96.0/19
2021-03-30 07:14:17 [ℹ]  subnets for eu-west-3a - public:192.168.32.0/19 private:192.168.128.0/19
2021-03-30 07:14:17 [ℹ]  subnets for eu-west-3c - public:192.168.64.0/19 private:192.168.160.0/19
2021-03-30 07:14:17 [ℹ]  using Kubernetes version 1.19
2021-03-30 07:14:17 [ℹ]  creating EKS cluster "dev" in "eu-west-3" region with managed nodes
2021-03-30 07:14:17 [ℹ]  1 nodegroup (spot-1) was included (based on the include/exclude rules)
2021-03-30 07:14:17 [ℹ]  will create a CloudFormation stack for cluster itself and 0 nodegroup stack(s)
2021-03-30 07:14:17 [ℹ]  will create a CloudFormation stack for cluster itself and 1 managed nodegroup stack(s)
2021-03-30 07:14:17 [ℹ]  if you encounter any issues, check CloudFormation console or try 'eksctl utils describe-stacks --region=eu-west-3 --cluster=dev'
2021-03-30 07:14:17 [ℹ]  CloudWatch logging will not be enabled for cluster "dev" in "eu-west-3"
2021-03-30 07:14:17 [ℹ]  you can enable it with 'eksctl utils update-cluster-logging --enable-types={SPECIFY-YOUR-LOG-TYPES-HERE (e.g. all)} --region=eu-west-3 --cluster=dev'
2021-03-30 07:14:17 [ℹ]  Kubernetes API endpoint access will use default of {publicAccess=true, privateAccess=false} for cluster "dev" in "eu-west-3"
2021-03-30 07:14:17 [ℹ]  2 sequential tasks: { create cluster control plane "dev", 3 sequential sub-tasks: { 2 sequential sub-tasks: { wait for control plane to become ready, tag cluster }, create addons, create managed nodegroup "spot-1" } }
2021-03-30 07:14:17 [ℹ]  building cluster stack "eksctl-dev-cluster"
2021-03-30 07:14:18 [ℹ]  deploying stack "eksctl-dev-cluster"
2021-03-30 07:14:48 [ℹ]  waiting for CloudFormation stack "eksctl-dev-cluster"
[...]
2021-03-30 07:26:20 [ℹ]  waiting for CloudFormation stack "eksctl-dev-cluster"
2021-03-30 07:26:22 [✔]  tagged EKS cluster (role=devops)
2021-03-30 07:26:22 [ℹ]  building managed nodegroup stack "eksctl-dev-nodegroup-spot-1"
2021-03-30 07:26:23 [ℹ]  deploying stack "eksctl-dev-nodegroup-spot-1"
2021-03-30 07:26:23 [ℹ]  waiting for CloudFormation stack "eksctl-dev-nodegroup-spot-1"
[...]
2021-03-30 07:29:57 [ℹ]  waiting for CloudFormation stack "eksctl-dev-nodegroup-spot-1"
2021-03-30 07:29:58 [ℹ]  waiting for the control plane availability...
2021-03-30 07:29:58 [✔]  saved kubeconfig as "/runner/.kube/config"
2021-03-30 07:29:58 [ℹ]  no tasks
2021-03-30 07:29:58 [✔]  all EKS cluster resources for "dev" have been created
2021-03-30 07:29:58 [ℹ]  nodegroup "spot-1" has 4 node(s)
2021-03-30 07:29:58 [ℹ]  node "ip-192-168-14-116.eu-west-3.compute.internal" is ready
2021-03-30 07:29:58 [ℹ]  node "ip-192-168-25-138.eu-west-3.compute.internal" is ready
2021-03-30 07:29:58 [ℹ]  node "ip-192-168-46-234.eu-west-3.compute.internal" is ready
2021-03-30 07:29:58 [ℹ]  node "ip-192-168-78-146.eu-west-3.compute.internal" is ready
2021-03-30 07:29:58 [ℹ]  waiting for at least 4 node(s) to become ready in "spot-1"
2021-03-30 07:29:58 [ℹ]  nodegroup "spot-1" has 4 node(s)
2021-03-30 07:29:58 [ℹ]  node "ip-192-168-14-116.eu-west-3.compute.internal" is ready
2021-03-30 07:29:58 [ℹ]  node "ip-192-168-25-138.eu-west-3.compute.internal" is ready
2021-03-30 07:29:58 [ℹ]  node "ip-192-168-46-234.eu-west-3.compute.internal" is ready
2021-03-30 07:29:58 [ℹ]  node "ip-192-168-78-146.eu-west-3.compute.internal" is ready
2021-03-30 07:30:00 [ℹ]  kubectl command should work with "/runner/.kube/config", try 'kubectl --kubeconfig=/runner/.kube/config get nodes'
2021-03-30 07:30:00 [✔]  EKS cluster "dev" in "eu-west-3" region is ready
2021-03-30 07:30:01 [ℹ]  eksctl version 0.42.0
2021-03-30 07:30:01 [ℹ]  using region eu-west-3
2021-03-30 07:30:02 [ℹ]  will create IAM Open ID Connect provider for cluster "dev" in "eu-west-3"
2021-03-30 07:30:02 [✔]  created IAM Open ID Connect provider for cluster "dev" in "eu-west-3"

Set OIDC provider (to bind role to service account)
2021-03-30 07:30:07 [ℹ]  eksctl version 0.42.0
2021-03-30 07:30:07 [ℹ]  using region eu-west-3
2021-03-30 07:30:08 [ℹ]  IAM Open ID Connect provider is already associated with cluster "dev" in "eu-west-3"

Create context in kubeconfig
Updated context dev in /project/do.kubeconfig

Generate logging configuration
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100 16485  100 16485    0     0  81206      0 --:--:-- --:--:-- --:--:-- 80808

Set logging
namespace/amazon-cloudwatch unchanged
serviceaccount/cloudwatch-agent unchanged
clusterrole.rbac.authorization.k8s.io/cloudwatch-agent-role unchanged
clusterrolebinding.rbac.authorization.k8s.io/cloudwatch-agent-role-binding unchanged
configmap/cwagentconfig unchanged
daemonset.apps/cloudwatch-agent unchanged
configmap/fluent-bit-cluster-info created
serviceaccount/fluent-bit unchanged
clusterrole.rbac.authorization.k8s.io/fluent-bit-role unchanged
clusterrolebinding.rbac.authorization.k8s.io/fluent-bit-role-binding unchanged
configmap/fluent-bit-config unchanged
daemonset.apps/fluent-bit unchanged
```

## Create docker registry
```shell
./do --component=eks_cluster --command=aws_apply                                                                                                                                                                                                                 <aws:tdeheurles_api>
```
should output something like
```shell
Build Succeeded

Built Artifacts  : components/eks_cluster/cloudformation/.aws-sam/build
Built Template   : components/eks_cluster/cloudformation/.aws-sam/build/template.yaml

Commands you can use next
=========================
[*] Invoke Function: sam local invoke -t components/eks_cluster/cloudformation/.aws-sam/build/template.yaml
[*] Deploy: sam deploy --guided --template-file components/eks_cluster/cloudformation/.aws-sam/build/template.yaml



        SAM CLI now collects telemetry to better understand customer needs.

        You can OPT OUT and disable telemetry collection by setting the
        environment variable SAM_CLI_TELEMETRY=0 in your shell.
        Thanks for your help!

        Learn More: https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-telemetry.html



Successfully packaged artifacts and wrote output template to file /project/components/eks_cluster/cloudformation/.aws-sam/build/packaged-template.yaml.
Execute the following command to deploy the packaged template
sam deploy --template-file /project/components/eks_cluster/cloudformation/.aws-sam/build/packaged-template.yaml --stack-name <YOUR STACK NAME>



        Deploying with following values
        ===============================
        Stack name                   : eks-cluster-additional
        Region                       : None
        Confirm changeset            : False
        Deployment s3 bucket         : None
        Capabilities                 : ["CAPABILITY_IAM", "CAPABILITY_NAMED_IAM"]
        Parameter overrides          : {}
        Signing Profiles             : {}

Initiating deployment
=====================

Waiting for changeset to be created..

CloudFormation stack changeset
-------------------------------------------------------------------------------------------------
Operation                LogicalResourceId        ResourceType             Replacement
-------------------------------------------------------------------------------------------------
+ Add                    kpadmin                  AWS::ECR::Repository     N/A
-------------------------------------------------------------------------------------------------

Changeset created successfully. arn:aws:cloudformation:...:...:changeSet/samcli-deploy1617095610/ff4750a6-e87d-48ce-81d6-bf03a42c513e


2021-03-30 09:13:36 - Waiting for stack create/update to complete

CloudFormation events from changeset
-------------------------------------------------------------------------------------------------
ResourceStatus           ResourceType             LogicalResourceId        ResourceStatusReason
-------------------------------------------------------------------------------------------------
CREATE_IN_PROGRESS       AWS::ECR::Repository     kpadmin                  -
CREATE_COMPLETE          AWS::ECR::Repository     kpadmin                  -
CREATE_IN_PROGRESS       AWS::ECR::Repository     kpadmin                  Resource creation
                                                                           Initiated
CREATE_COMPLETE          AWS::CloudFormation::S   eks-cluster-additional   -
                         tack
-------------------------------------------------------------------------------------------------

Successfully created/updated stack - eks-cluster-additional in None
```
