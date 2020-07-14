#include "SocketTCP.hpp"

     int main (int argc, char* argv[]) {
   
           if(argc!=4) {
            printf("USAGE: %s IP PORT MSG\n", argv[0]); 
           return -1;
	}
   
        char* dest_ip = argv[1];
        int dest_port = atoi(argv[2]);
        char* request = argv[3];

        TCP_Client myself = TCP_Client();
 
     if(!myself.connetti(dest_ip, dest_port)) {
      	printf("Cannot open connection to %s:%d\n", dest_ip, dest_port);
      return -2;
    }
   
    myself.invia(request);
    char* answer = myself.ricevi();

    printf("[%s:%d] %s\n", dest_ip, dest_port, answer);
   
    return 0;
   }
