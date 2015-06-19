#!/bin/bash

i=1
while IFS='' read -r line || [[ -n $line ]]; do
    echo -e "$line\t$i"
    ((i++))
done < "$1"
