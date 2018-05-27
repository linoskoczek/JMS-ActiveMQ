#!/bin/bash
path='/home/marcin/ownCloud/IdeaProjects/TPO/apache-activemq-5.15.4'
rm $path/data/activemq.pid
rm $path/data/audit.log
rm -r $path/data/kahadb
pid=$( /home/marcin/ownCloud/IdeaProjects/TPO/apache-activemq-5.15.4/bin/activemq start | grep 'pid' | tail -c 10 | cut -d"'" -f 2 )
echo $pid
read
kill $pid
echo "activemq killed"
