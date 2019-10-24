set -l arr
for i in (cat requirements_importantslides)
	set -a arr $i
end

pdftk RR.pdf cat $arr output requirements_condensed.pdf
