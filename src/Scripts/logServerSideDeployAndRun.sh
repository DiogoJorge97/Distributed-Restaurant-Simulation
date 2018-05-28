echo "Compressing data to be sent to the log server side node."
rm -rf logServerSide.zip
zip -rq logServerSide.zip kitchen bar table chef waiter student comInf entities_states generalRepository semaphore stubs  ClientSideGeral
echo "Transfering data to the server side node."
sshpass -f password ssh cd0208@l040101-ws01.ua.pt 'mkdir -p CDSockets/RestauranteSocket'
sshpass -f password ssh cd0208@l040101-ws01.ua.pt 'rm -rf CDSockets/RestauranteSocket/*'
sshpass -f password scp logServerSide.zip cd0208@l040101-ws01.ua.pt:CDSockets/RestauranteSocket
echo "Decompressing data sent to the server side node."
sshpass -f password ssh cd0208@l040101-ws01.ua.pt 'cd CDSockets/RestauranteSocket ; unzip -uq logServerSide.zip'
echo "Compiling program files at the server side node."
sshpass -f password ssh cd0208@l040101-ws01.ua.pt 'cd CDSockets/RestauranteSocket ; javac */*.java'
echo "End of compiling at the log server side node."
sleep 1
echo "Executing log program at the server side node."
sshpass -f password ssh cd0208@l040101-ws01.ua.pt 'cd CDSockets/RestauranteSocket ; java generalRepository.GeneralRepoMain'
echo "Server  shutdown."

