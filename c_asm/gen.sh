#!/bin/bash

FST="$1"

sort "$FST" > "$FST.sort"

FIRST_CALL=1

cat <<EOF
int pos=0;
int total=0;

EOF

while read DEP ARR CHAR WEIGHT ; do
    : ${WEIGHT:=0}

    if [[ $DEP != $PREV_DEP ]]; then
        if [[ ! "$FIRST_CALL" ]]; then
            cat <<EOF
    }

EOF
        fi

        cat <<EOF
NODE_$DEP :
    pos++;
    switch (token[pos-1]) {
EOF
    fi

    cat <<EOF
    case '$CHAR':
        total += $WEIGHT;
        goto NODE_$ARR;
EOF

    PREV_DEP=$DEP
    FIRST_CALL=
done < "$FST.sort"

rm "$FST.sort"
