(cd ./Development_Guide/; publican build --formats pdf,html --langs en-US --config publican_en.cfg)
cp -a ./Development_Guide/tmp/en-US/pdf/. ./dist

(cd ./Administration_Guide/; publican build --formats pdf,html --langs en-US --config publican_en.cfg)
cp -a ./Administration_Guide/tmp/en-US/pdf/. ./dist

ant -file ./HtmlConvertor/build.xml

java -cp ./HtmlConvertor/lib/jsoup-1.7.3.jar:./HtmlConvertor/build/classes com.eas.htmlconvertor.HtmlConvertor -src ./Development_Guide/tmp/en-US/html -dest ./dist/Development_Guide -layout ./HtmlLayout 

java -cp ./HtmlConvertor/lib/jsoup-1.7.3.jar:./HtmlConvertor/build/classes com.eas.htmlconvertor.HtmlConvertor -src ./Administration_Guide/tmp/en-US/html -dest ./dist/Administration_Guide -layout ./HtmlLayout
