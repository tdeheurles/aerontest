FROM openjdk:15

ENV PROJECT /project
COPY build/libs/Cluster-1.0-SNAPSHOT.jar ${PROJECT}/service.jar
COPY runner.exec_common.sh                      ${PROJECT}/exec_common.sh
COPY entrypoint.sh                       ${PROJECT}/entrypoint.sh

ENTRYPOINT ["/project/entrypoint.sh"]
