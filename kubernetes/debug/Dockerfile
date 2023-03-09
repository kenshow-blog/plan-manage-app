FROM centos:7

# systemdを有効化する
ENV container docker
RUN (cd /lib/systemd/system/sysinit.target.wants/; for i in *; do [ $i == systemd-tmpfiles-setup.service ] || rm -f $i; done)
RUN rm -f /lib/systemd/system/multi-user.target.wants/*
RUN rm -f /etc/systemd/system/*.wants/*
RUN rm -f /lib/systemd/system/local-fs.target.wants/*
RUN rm -f /lib/systemd/system/sockets.target.wants/*udev*
RUN rm -f /lib/systemd/system/sockets.target.wants/*initctl*
RUN rm -f /lib/systemd/system/basic.target.wants/*
RUN rm -f /lib/systemd/system/anaconda.target.wants/*
VOLUME [ "/sys/fs/cgroup" ]
CMD ["/usr/sbin/init"]

# PostgreSQL 12をインストールする
RUN yum update -y && \
    yum install -y https://download.postgresql.org/pub/repos/yum/reporpms/EL-7-x86_64/pgdg-redhat-repo-latest.noarch.rpm && \
    yum install -y postgresql12-server postgresql12-contrib

# PostgreSQLのデータディレクトリの作成
RUN mkdir -p /var/lib/pgsql/12/data
RUN chown -R postgres:postgres /var/lib/pgsql/12/data

# 初期化
RUN su postgres -c "/usr/pgsql-12/bin/initdb -D /var/lib/pgsql/12/data"

# PostgreSQLの自動起動を有効化
RUN systemctl enable postgresql-12.service

# ポートを開ける
EXPOSE 5432

# コンテナ起動時にpostgresqlを起動
CMD ["/usr/sbin/init"]

