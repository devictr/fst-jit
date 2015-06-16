#!/bin/bash

FST="$1"

sort "$FST" > "$FST.sort"

FIRST_CALL=1

cat <<EOF
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
    }

NODE_$DEP :
    total += $WEIGHT;
    goto END;
EOF

        continue
    fi

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

cat <<EOF

END :
    return total;
}
EOF

rm "$FST.sort"
