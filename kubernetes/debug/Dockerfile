FROM centos:7

RUN \
yum -y install https://download.postgresql.org/pub/repos/yum/reporpms/EL-7-x86_64/pgdg-redhat-repo-latest.noarch.rpm \
yum -y install postgresql-server \
# Install ip , ifconfig command.
yum install -y iproute net-tools; \
# Install jq
curl -o /usr/local/bin/jq -L https://github.com/stedolan/jq/releases/download/jq-1.6/jq-linux64; \
chmod +x /usr/local/bin/jq; \
# Delete cache files.
yum clean all;
