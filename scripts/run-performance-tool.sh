while getopts t:i:I: flag
do
    case "${flag}" in
        t) threads=${OPTARG};;
        i) insertsPerCommit=${OPTARG};;
        I) totalInserts=${OPTARG};;
    esac
done

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
java -jar -Dnumber.of.threads="${threads}" \
-Dnumber.of.insert.statements.per.commit="${insertsPerCommit}" \
-Dtotal.number.of.insert.statements="${totalInserts}" \
$DIR/../target/postgre-performance-tool-1.0-SNAPSHOT-jar-with-dependencies.jar