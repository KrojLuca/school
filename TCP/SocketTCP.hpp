#ifndef __SOCKET_TCP__HPP__ 
#define __SOCKET_TCP__HPP__

#include <sys/socket.h>
#include <arpa/inet.h> 
#include <stdio.h> 
#include <stdlib.h> 
#include <unistd.h> 
#include <string.h>
#include <list>

#define LO "127.0.0.1" 
#define DHCP "0.0.0.0"

#define MAX_MSG 4096

class Address { 
      private : 
        //properties
        char* ip;
	int port;
        public:

        //methods
        Address(char* ip,int port) {
            this->ip = strdup(ip); 
	    this->port = port;
	}

        Address(const Address &address) { 
            ip = strdup(address.ip);
            port = address.port;
        }

	Address(struct sockaddr_in address) {
            ip = strdup(inet_ntoa(address.sin_addr)); 
            port = ntohs(address.sin_port);
	}

        ~Address() { free(ip); }

	int get_port() { return port; }

        char* get_ip() { return strdup(ip); } 

        struct sockaddr_in* get_address() {
            struct sockaddr_in * ret = (struct sockaddr_in*) malloc(sizeof(struct sockaddr_in));
            ret->sin_family = AF_INET;
	    inet_aton(ip, &(ret->sin_addr));
            ret->sin_port = htons(port);
            for (int i=0; i<8 ; i++) ret->sin_zero[i] = 0;
            return ret;
	}

};

class Connection { 
	protected:
        //properties
	int id = 0;
        private:
        Address* address;
        public:

        //methods
        Connection(int id, Address address) {
	    this->id = id;
            this->address = new Address(address);
        }

        Connection(int id, struct sockaddr_in address) {
            this->id = id;
	    this->address = new Address(address);
	}
   
        bool invia(char*);
        bool invia_raw(void*, int);
        char* ricevi();
	bool ricevi_raw(void*, int*);
        Address get_address() { return Address(*address); }
        ~Connection() { if(address) free(address);}
     };

bool Connection::invia(char* msg) { 
        int msg_len = strlen(msg) + 1;
        return invia_raw((void*)msg, msg_len);
	
 }
      
bool Connection::invia_raw(void* buffer, int len) {
	int rc = send(id, buffer, len, 0);
        return (rc != len);
}

char* Connection::ricevi() {
          char buffer[MAX_MSG+1];
	  int max = MAX_MSG;
          if (ricevi_raw((void*)buffer, &max)) {
              buffer[max] = '\0';
              return strdup(buffer);
	  }
	   else return NULL;
	}

bool Connection::ricevi_raw(void* buffer, int *max) { 
          *max = recv(id, buffer, *max, 0);
	   return (*max !=0);
}

class Client_Connection: public Connection {
          public:
	  Client_Connection(int id, Address address): Connection(id, address) {}
          ~Client_Connection() {}
      };

      class Server_Connection: public Connection {
          public:
          Server_Connection(int id, Address address): Connection(id, address) {}
          ~Server_Connection() {     shutdown(id, SHUT_RDWR); }
};

    
class TCP_Socket {
          protected:     int id; 
          public:
          TCP_Socket(char* ip,int port = 0);
          ~TCP_Socket();
};

      
TCP_Socket::TCP_Socket(char* ip,int port) {
          this->id = socket(AF_INET, SOCK_STREAM, 0);

          if (port) {
              struct sockaddr_in address;
              address.sin_family = AF_INET;
              inet_aton(ip, &address.sin_addr);
              address.sin_port = htons(port);
              for (int i= 0; i<8; i++) address.sin_zero[i] = 0;

              bind(id, (struct sockaddr*) &address, (socklen_t) sizeof(struct sockaddr_in));
      }
}
    
TCP_Socket::~TCP_Socket() { close(id); }

class TCP_Client: public TCP_Socket { 
          private: Client_Connection* conn; 
          public:
          TCP_Client(): TCP_Socket(NULL, 0) {}
          ~TCP_Client() { if(conn) delete(conn); } 
          bool connetti(Address server);
          bool connetti(char*ip, int port);
          bool     invia(char* msg) { return conn->invia(msg); }
          bool     invia_raw(void* buffer, int len) { return conn->invia_raw(buffer, len); }
          char*    ricevi() { return conn->ricevi(); }
          bool     ricevi_raw(void* buffer,int *max) { return conn->ricevi_raw(buffer, max); }
};

bool TCP_Client::connetti(Address server) {
              struct sockaddr_in* server_address = server.get_address();
              int rc = connect(id, (struct sockaddr*) server_address, (socklen_t) sizeof(struct sockaddr_in));
              if (rc != 0) {
                free(server_address);
                return false;
            }

            conn = new Client_Connection(id, *server_address);
            free(server_address);
            return true;
}
     
bool TCP_Client::connetti(char* ip,int port) {
        Address* address = new Address(ip, port);
        bool ret = connetti(*address);
        free(address);
        return ret;
}

class TCP_Server: public TCP_Socket {
        private:   std::list <Server_Connection*> *connections;
        public:
        TCP_Server(char* ip,int port = 0): TCP_Socket(ip, port) { 
            listen(id, 50);
            connections = new std::list<Server_Connection*>();
	}
        Server_Connection* accetta();
        bool    chiudi(Server_Connection*);
        bool    chiudi_tutto();
        ~TCP_Server() {
            chiudi_tutto();
            delete(connections); }
};

Server_Connection* TCP_Server::accetta() {
        struct sockaddr_in address;
        int len = sizeof(struct sockaddr_in);
        int rc = accept(id, (struct sockaddr*)&address, (socklen_t*) &len); 
        Server_Connection* conn = new Server_Connection(rc, address);
        connections->push_back(conn);
        return conn;
}

bool   TCP_Server::chiudi(Server_Connection* conn) {
        connections->remove(conn);
        delete(conn);
}

bool   TCP_Server::chiudi_tutto() {
        std::list<Server_Connection*>::iterator it;
        for(it = connections->begin(); it != connections->end(); it++) { 

            delete((Server_Connection*)(*it));
	}
        connections->clear();
     }
#endif /*__SOCKET_TCP__HPP__*/

