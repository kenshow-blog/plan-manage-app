# plan-manage-app

## About this App

This is a managing plan application

Once you create a new plan, you can see not only the plan's content but also the related infomations such as wheather info.

![アプリ画像](https://user-images.githubusercontent.com/85926984/221210558-41240259-7b90-45c4-9433-e0deead2dd85.png)

## Technical stack

**frontend**

- react
- redux-toolkit
- typescript

**backend**

- Kotlin
- Spring Boot
- Clean Architecture

![image](https://user-images.githubusercontent.com/65284319/221415283-5abad695-ee52-4848-b977-05a936ccc886.png)

## Getting Start

1. `$ git clone https://github.com/kenshow-blog/plan-manage-app.git`

2. `$ docker-compose up -d`

3. open plan-manager-backend by IDE (I reccomend to use IDEA)

4. execute PlanManagerBackendApplication/main

5. open plan-manager-frontend by IDE (I reccomend to use VSCode)

6. `$ yarn`

7. `$ yarn run dev`

## memo

solved docker problem by https://github.com/docker/for-mac/issues/6646.
1068 minikube image ls
1069 minikube image load debug
1070 minikube image ls
1071 kubectl get pod
1072 kubectl exec -it debug sh

### Terminating になっている pvc を削除する

```sh
kubectl patch pvc nfs-data-volume -p '{"metadata":{"finalizers": []}}' --type=merge
```

### docker では、デフォルトで systemctl を実行することができない

実行すると下記のエラーが起きる

```
#16 0.270 Failed to get D-Bus connection: Operation not permitted
#16 0.271 failed to find PGDATA setting in postgresql-12.service
```

下記を Dockerfile に追記することで解消

```Dockerfile
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
```

### minkube 上で作成した db サーバーに外部からアクセスする方法

```
minikube service <serviceオブジェクト名>
```

で外部エンドポイントが作成されるのでそのエンドポイントを使用する。
