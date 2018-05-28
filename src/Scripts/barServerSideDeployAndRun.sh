echo "Compressing data to be sent to the bar server side node."
pwd
rm -rf barServerSide.zip
zip -rq barServerSide.zip kitchen bar table chef waiter student comInf entities_states generalRepository semaphore stubs 
echo "Transfering data to the server side node."
sshpass -f password ssh cd0208@l040101-ws03.ua.pt 'mkdir -p CDSockets/RestauranteSocket'
sshpass -f password ssh cd0208@l040101-ws03.ua.pt 'rm -rf CDSockets/RestauranteSocket/*'
sshpass -f password scp barServerSide.zip cd0208@l040101-ws03.ua.pt:CDSockets/RestauranteSocket
echo "Decompressing data sent to the server side node."
sshpass -f password ssh cd0208@l040101-ws03.ua.pt 'cd CDSockets/RestauranteSocket ; unzip -uq barServerSide.zip'
echo "Compiling program files at the server side node."
sshpass -f password ssh cd0208@l040101-ws03.ua.pt 'cd CDSockets/RestauranteSocket ; javac */*.java'
echo "End of compiling at the bar server side node."
sleep 1
echo "Executing bar program at the server side node."
sshpass -f password ssh cd0208@l040101-ws03.ua.pt 'cd CDSockets/RestauranteSocket ; java bar.BarMain'
echo "Server  shutdown."

