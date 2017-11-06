This project scans through a file system downloaded using “wget -r --no-parent WEBSITE URL”

After this file system was downloaded, it uses a recursive method (taken from Duke CompSci department) to check all folders for certain files.

The file type is specified to have the .html extension or to have no extension - since these are the two files containing text. 

The text is compiled into a larger text file and cleaned of stop words, single letters, and HTML tags and phrases (e.g. ffffff, div, href, etc). NOTE: not every HTML tags is removed. 

Then word frequency analysis is used. The most frequently occurring words are outputted in order from least to most.   

