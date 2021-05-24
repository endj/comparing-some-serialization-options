# Comparing some serialization options
And testing out some other things. To generate the protobuff code, you need to install the compiler and run ```mvn clean install -P protobuff```

### Java						

||||||||
| ------------|-----|------|---------|------|--------|--------|						
|objectCount |	100 |	500|	1000|	5000|	10000|	15000 |
|writeDurationMs |	8 |	31 |	60 |	282 |	563 |	869| 
|readDurationMs |	9 |	36 |	68 |	303 |	613 |	929| 
|fileSizeBytes |	50540 |	249392 |	499422 |	2487021 |	4959441 |	7431582| 
|fileSizeKb |	49 |	243 |	487 |	2428 |	4843 |	7257| 
						
### Jackson				
	
||||||||
| ------------|-----|------|---------|------|--------|--------|							
|objectCount|	100|	500|	1000|	5000|	10000|	15000|
|writeDurationMs|	0|	1|	2|	12|	24|	34|
|readDurationMs|	0|	3|	5|	43|	76|	107|
|fileSizeBytes|	44837|	228360|	447986|	2250568|	4493912|	6756109|
|fileSizeKb|	43|	223|	437|	2197|	4388|	6597|
						
### Fst	

||||||||
| ------------|-----|------|---------|------|--------|--------|						
|objectCount|	100|	500|	1000|	5000|	10000|	15000|
|writeDurationMs|	1|	3|	7|	36|	77|	178|
|readDurationMs|	1|	3|	7|	26|	62|	130|
|fileSizeBytes|	40450|	201385|	400906|	2005463|	4013132|	6012058|
|fileSizeKb|	39|	196|	391|	1958|	3919|	5871|
