default: build

VERSION = 0.1.0

.PHONY: build
build:
	mvn clean install

.PHONY: run
run:
	java -jar ./target/worldpay-api-${VERSION}.jar

.PHONY: all
all: build run
