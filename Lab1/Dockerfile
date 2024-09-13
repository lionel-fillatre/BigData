FROM ubuntu:22.04

# install packages
RUN \
  apt-get update && apt-get install -y \
  default-jdk \
  curl

# install python and Jupyterlab
RUN \
  apt-get install -y python3-pip && \
  pip install jupyter -U && \
  pip install jupyterlab

# download coursier (check automatically your system)
RUN if [ $(uname -m) = "aarch64" ]; then \ 
	curl -fL "https://github.com/VirtusLab/coursier-m1/releases/latest/download/cs-aarch64-pc-linux.gz" | gzip -d > cs; \
elif [ $(uname -m) = "x86_64" ]; then \ 
	curl -fL "https://github.com/coursier/launchers/raw/master/cs-x86_64-pc-linux.gz" | gzip -d > cs; \
fi

# install coursier, scala and almond
RUN \
  chmod +x cs && \
  echo "export PATH=$PATH:/root/.local/share/coursier/bin" >> ~/.bashrc && \
  ./cs install scala:3.4.0 && \
  curl -Lo coursier https://git.io/coursier-cli && \
  chmod +x coursier && \
  ./coursier launch --fork almond -- --install && \
  rm -f coursier

# expose various ports
EXPOSE 8888

# User directory
WORKDIR /home/user

ENTRYPOINT ["jupyter", "lab","--ip=0.0.0.0","--allow-root","--LabApp.token='datatoken'"]