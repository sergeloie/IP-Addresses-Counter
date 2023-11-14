run-small:
	./build/install/app-shadow/bin/app ./src/test/resources/small

run-small-debug:
	./build/install/app-shadow/bin/app -d ./src/test/resources/small

run-large:
	./build/install/app-shadow/bin/app /mnt/2TB/inc/Download/ip_addresses/ip_addresses

run-large-debug:
	./build/install/app-shadow/bin/app -d /mnt/2TB/inc/Download/ip_addresses/ip_addresses

build:
	./gradlew clean installShadowDist

report:
	@./gradlew jacocoTestReport

run-movie:
	make run-small
	make run-small-debug

asciinema:
	clear
	asciinema rec -c "make run-movie"
sonar:
	./gradlew build sonar --info

.PHONY: build
