# Postgres Performance Tool
A tool for measuring the performance of a postgres server.

## Installation
Run the initialization script:

``bash scripts/init-script.sh``

This script ...
1) builds the performance tool,
2) builds the docker image for the postgres container, and
3) runs the postgres container at port 5432.

## Performance tool usage
Run the script:

``bash scripts/run-performance-tool.sh -t 2 -i 5 -I 10``

where the parameter ...
1) t > 0 represents the number of threads,
2) i > 0 represents the number of insert statements per commit per thread, and
3) I >= t*i represents the total number of insert statements.