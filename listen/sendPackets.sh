#!/bin/bash

declare -i count=0



for j in {1..2}
do
	echo -e "0178CAFE01DD000F3133343536373839303132333435080400000113fc208dff000f14f650209cca80006f00d60400040004030101150316030001460000015d0000000113fc17610b000f14ffe0209cc580006e00c00500010004030101150316010001460000015e0000000113fc284945000f150f00209cd200009501080400000004030101150016030001460000015d0000000113fc267c5b000f150a50209cccc0009300680400000004030101150016030001460000015b0004" >/dev/udp/localhost/$1
	count=$((count+1))
sleep 0.02
done
echo "Cycle complete"
echo $count