DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
echo "***** Building jar *****"
bash $DIR/build-performance-tool.sh

echo "***** Building docker image *****"
bash $DIR/build-docker-image.sh

docker rm -f my-postgres-container &>/dev/null && echo "***** Removing old container *****"

echo "***** Run docker container *****"
bash $DIR/run-docker-container.sh