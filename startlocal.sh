main() {
    loadEnv
    build
    start
}

loadEnv() {
    if [ -f .env ]
    then
        set -a
        . ./.env
        set +a
    fi
}

check_rs() {
    if [[ "$1" -ne 0 ]]; then
        echo "Could not perform on local"
        exit 1
    fi
}

build() {
    ehco "Building local..."
    mvn clean install
    check_rs "$?"
}

start() {
    java -jar ./target/wemoment-0.0.1-SNAPSHOT.jar
    check_rs "$?"
}

main "$@"; exit