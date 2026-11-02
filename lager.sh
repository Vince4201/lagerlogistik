#!/usr/bin/env bash
# Startet die Lagerverwaltung. Hat sich der Quellcode geändert, wird vorher neu gebaut.
# Aufruf: ./lager.sh [datenbankdatei]   (Standard: lager.db im Projektordner)
set -euo pipefail

DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
JAR="$DIR/target/lagerverwaltung.jar"

if [[ ! -f "$JAR" || -n "$(find "$DIR/pom.xml" "$DIR/src" -newer "$JAR" -print -quit)" ]]; then
    echo "Lagerverwaltung wird gebaut (beim ersten Mal dauert das etwas) ..."
    # Maven selbst löst ab Java 24 harmlose Unsafe-Warnungen aus; die werden hier abgeschaltet.
    if java --sun-misc-unsafe-memory-access=allow -version >/dev/null 2>&1; then
        export MAVEN_OPTS="${MAVEN_OPTS:-} --sun-misc-unsafe-memory-access=allow"
    fi
    mvn -q -B -f "$DIR/pom.xml" package
fi

exec java -jar "$JAR" "${1:-$DIR/lager.db}"
