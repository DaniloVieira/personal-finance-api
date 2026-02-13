FROM ubuntu:latest
LABEL authors="danilo"

ENTRYPOINT ["top", "-b"]