# KUBERNETES AUTH

## Setup local docker desktop
- install docker
- copy your local kubeconfig to the project root
```shell
# cd project_root
cp ~/.kube/config do.kubeconfig
```
- for windows:
    - replace context name
    ```shell
    # cd project_root
    sed -i "s|docker-desktop|aerontest-local|g" do.kubeconfig
    ```
