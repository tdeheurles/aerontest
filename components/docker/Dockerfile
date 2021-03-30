FROM registry.gitlab.com/atyos/atyosrunner:1.4.1.a6f5bd12

ENV PROJECT           /project
ENV RUNNER_DOTENV_DIR ${PROJECT}
ENV COMPONENTS_DIR    ${PROJECT}/components

COPY do.config.env        ${PROJECT}/do.config.env
COPY components/binaries  ${PROJECT}/components/binaries

COPY components/messages  ${PROJECT}/components/messages
RUN cd ${PROJECT} && python3.8 "${RUNNER_HOME}/src/cli/do.py" --component="messages" --command="generate"

COPY components/babl      ${PROJECT}/components/ui
RUN cd ${PROJECT} && python3.8 "${RUNNER_HOME}/src/cli/do.py" --component="babl" --command="build"

COPY components/Cluster   ${PROJECT}/components/Cluster
RUN cd ${PROJECT} && python3.8 "${RUNNER_HOME}/src/cli/do.py" --component="Cluster" --command="gradle_build"

COPY components/ui        ${PROJECT}/components/ui
