FROM gradle:4.2.1-jdk8-alpine

WORKDIR /app

ADD --chown=gradle:gradle /bin/app/ .

ENTRYPOINT ["gradle"]

CMD ["run"]
