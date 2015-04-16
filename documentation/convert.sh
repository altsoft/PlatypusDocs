#!/bin/sh
#find . -name "*.asciidoc" -exec rm {} \;
find . -name \*.xml -type f -print -exec pandoc -f docbook -t markdown -s {} -o {}.md \; 
find . -name \*.md -type f -print -exec pandoc -f markdown -t asciidoc -s {} -o {}.asciidoc \; -exec rm {} \;
find . -name "*.xml.md.asciidoc" -print | xargs rename 's/\.xml.md.asciidoc$/\.asciidoc/'
find . -name "*.xml" -exec rm {} \;
