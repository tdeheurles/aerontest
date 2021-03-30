#!/usr/bin/env bash
set -euo pipefail

eksctl_version="<% eksctl_version %>"

this_directory="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
project_root="${this_directory}/../.."
(
    cd "${project_root}" || exit 1
    ./do --host --component="dependencies" --command="get_eksctl"

    cd "${this_directory}" || exit 1
    ../k8s_auth/use_context.sh

    color_0="\e[35m"
    color_1="\e[36m"
    color_no="\e[39m"
    kubesystem="kube-system"
    cloudwatch="amazon-cloudwatch"
    cluster_name="<% cluster_name %>"

    echo -e "${color_0}-- EKSCTL${color_no}"
    echo -e "${color_1}------ NODE GROUP ------ ${color_no}"
    "../binaries/eksctl-${eksctl_version}" get nodegroup --cluster "${cluster_name}"

    echo -e "\n${color_1}------ ADDON ------ ${color_no}"
    "../binaries/eksctl-${eksctl_version}" get addon --cluster "${cluster_name}"

    echo -e "\n${color_1}------ IAM IDENTITY MAPPING ------ ${color_no}"
    "../binaries/eksctl-${eksctl_version}" get iamidentitymapping --cluster "${cluster_name}"

    echo -e "\n${color_1}------ IAM SERVICE ACCOUNT ------ ${color_no}"
    "../binaries/eksctl-${eksctl_version}" get iamserviceaccount --cluster "${cluster_name}"

    echo -e "\n${color_0}-- KUBE-SYSTEM${color_no}"
    echo -e "${color_1}------ SERVICES ------ ${color_no}"
    kubectl get service -o wide -n ${kubesystem}

    echo -e "\n${color_1}------ DEPLOYMENT ------ ${color_no}"
    kubectl get deployment -o wide -n ${kubesystem}

    echo -e "\n${color_1}------ REPLICA SET ------ ${color_no}"
    kubectl get replicaset -o wide -n ${kubesystem}

    echo -e "\n${color_1}------ POD ------ ${color_no}"
    kubectl get pod -o wide -n ${kubesystem}

    echo -e "\n${color_0}-- CLOUDWATCH - FLUENT BIT${color_no}"
    echo -e "${color_1}------ DAEMON SET ------ ${color_no}"
    kubectl get daemonset -o wide -n ${cloudwatch}

    echo -e "\n${color_1}------ POD ------ ${color_no}"
    kubectl get pod -o wide -n ${cloudwatch}

    echo -e "\n${color_1}------ SERVICE ACCOUNT ------ ${color_no}"
    kubectl get serviceaccount -o wide -n ${cloudwatch}

    echo -e "\n${color_1}------ CLUSTER ROLE ------ ${color_no}"
    kubectl get clusterrole -o wide | grep cloudwatch-agent-role
    kubectl get clusterrole -o wide | grep fluent-bit-role

    echo -e "\n${color_1}------ CLUSTER ROLE BINDING ------ ${color_no}"
    kubectl get clusterrolebinding -o wide | grep cloudwatch-agent-role-binding
    kubectl get clusterrolebinding -o wide | grep fluent-bit-role-binding

    echo -e "\n${color_1}------ CONFIG_MAP ------ ${color_no}"
    kubectl get configmap -o wide -n ${cloudwatch}

    echo -e "\n${color_0}-- ENVIRONMENTS${color_no}"
    echo -e "\e[36m ------ SERVICES ------ \e[39m"
    kubectl get namespaces -o wide

    echo -e "\e[36m ------ SERVICES ------ \e[39m"
    kubectl get service -o wide --all-namespaces

    echo -e "\n\e[36m ------ DEPLOYMENT ------ \e[39m"
    kubectl get deployment -o wide --all-namespaces

    echo -e "\n\e[36m ------ REPLICA SET ------ \e[39m"
    kubectl get replicaset -o wide --all-namespaces

    echo -e "\n\e[36m ------ POD ------ \e[39m"
    kubectl get pod -o wide --all-namespaces
)
