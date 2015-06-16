#!/bin/bash

FST="$1"

sort "$FST" > "$FST.sort"

cat <<EOF
int pos=0;
int total=0;

EOF

while read DEP ARR CHAR WEIGHT ; do
    : ${WEIGHT:=0}

    if [[ $DEP != $PREV_DEP ]]; then
        cat <<EOF
    }

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
done < "$FST.sort"

rm "$FST.sort"
