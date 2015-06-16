#!/bin/bash

FST="$1"

sort "$FST" > "$FST.sort"

rm "$FST.sort"
