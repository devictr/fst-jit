#!/bin/bash

FST="$1"

sort "$FST" > "$FST.sort"

FIRST_CALL=1

cat <<EOF
#include <stdio.h>

int compute_fst(const char* token)
{
    int pos=0;
    int total=0;

EOF

while read DEP ARR CHAR WEIGHT ; do
    # If it's a final node
    if [[ ! "$CHAR" ]]; then
        WEIGHT=${ARR:-0}
        cat <<EOF
    default:
        return -1;
    }

NODE_$DEP :
EOF
    (( WEIGHT != 0 )) &&
        echo "    total += $WEIGHT;"
    echo "    goto END;"

        continue
    fi

    : ${WEIGHT:=0}

    if [[ $DEP != $PREV_DEP ]]; then
        if [[ ! "$FIRST_CALL" ]]; then
            cat <<EOF
    default:
        return -1;
    }

EOF
        fi

        cat <<EOF
NODE_$DEP :
    pos++;
    switch (token[pos-1]) {
EOF
    fi

    echo "    case '$CHAR':"
    (( WEIGHT != 0 )) &&
        echo "        total += $WEIGHT;"
    echo "        goto NODE_$ARR;"

    PREV_DEP=$DEP
    FIRST_CALL=
done < "$FST.sort"

cat <<EOF

END :
    return total;
}

int main(int argc, const char *argv[])
{
    if (argc < 2)
        return 1;

    printf("%d\n", compute_fst(argv[1]));
    return 0;
}
EOF

rm "$FST.sort"
