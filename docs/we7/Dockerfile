FROM centos:7

RUN yum install openssh-server -y

RUN yum install -y wget \
    && wget -O install.sh http://download.bt.cn/install/install_6.0.sh \
    && yes y|sh install.sh

# 修改root用户密码
RUN /bin/echo "123456" | passwd --stdin root


RUN ssh-keygen -t rsa -f etc/ssh/ssh_host_rsa_key \
    && ssh-keygen -t rsa -f etc/ssh/ssh_host_ecdsa_key \
    && ssh-keygen -t rsa -f etc/ssh/ssh_host_ed25519_key

ADD start_baota.sh home/start_baota.sh
RUN chmod +x home/start_baota.sh

RUN /etc/init.d/bt default \
    && yes username|bt 6 \
    && yes password|bt 5

EXPOSE 22

CMD ["sh", "/home/start_baota.sh"]