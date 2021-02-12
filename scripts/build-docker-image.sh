DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
docker build --no-cache -f $DIR/../docker/Dockerfile -t my-postgres-image $DIR/..