if [ $# -ne 1 ]; then
  echo "Usage:"
  echo "$0 <key>"
  exit 1
fi

mvn exec:java -pl TranslatingSysTray-app -Dexec.mainClass=org.gmol.TranslatingSysTray.App -Dexec.args="$1"
