MY_CROSS_COMPILE := arm-angstrom-linux-gnueabi-
SRC := $(shell pwd)
MY_LDFLAGS := -lstdc++ $(DEBUG) -mfloat-abi=hard
MY_CFLAGS := -c $(DEBUG) -Wreorder -mfloat-abi=hard
MY_CPP = $(MY_CROSS_COMPILE)g++
#DEBUG := -g -O0 -fno-inline
DEBUG :=

MMLINK_OBJS := udp_log.o mmlink_server.o mmlink_connection.o mm_debug_link.o main.o

.PHONY: all
all: mmlink

mmlink: $(MMLINK_OBJS)
	$(MY_CPP) $(MY_LDFLAGS) $(MMLINK_OBJS) -o $@

# To enable udp logging, add 
#   -D MM_LINK_UDP_DEBUG
# To capture udp log messages on the computer 
# at the given IP address: nc -luv 4444
udp_log.o: udp_log.cpp udp_log.h
	$(MY_CPP) $(MY_CFLAGS) \
	  -D MM_LINK_UDP_DEBUG \
	  -D MM_LINK_UDP_DEBUG_IP=\"192.168.10.1\" \
	  -D MM_LINK_UDP_DEBUG_PORT=4444 \
	  udp_log.cpp

mmlink_server.o: mmlink_server.cpp mmlink_server.h mmlink_connection.h mm_debug_link_interface.h dprint.h
	$(MY_CPP) $(MY_CFLAGS) mmlink_server.cpp

mmlink_connection.o: mmlink_connection.cpp mmlink_connection.h dprint.h
	$(MY_CPP) $(MY_CFLAGS) mmlink_connection.cpp

MM_DEBUG_LINK_IMPL := mm_debug_link_linux.cpp
mm_debug_link.o: mm_debug_link_linux.cpp mm_debug_link_linux.h dprint.h
	$(MY_CPP) $(MY_CFLAGS) $(MM_DEBUG_LINK_IMPL) -o mm_debug_link.o

main.o: main.cpp mmlink_server.h mm_debug_link_interface.h udp_log.h
	$(MY_CPP) $(MY_CFLAGS) main.cpp

###
# "test" is a simple application which writes data to the driver 
# without need for a socket connection from the host.
TEST_OBJS := udp_log.o mm_debug_link.o test.o
test: $(TEST_OBJS)
	$(MY_CPP) $(MY_LDFLAGS) $(TEST_OBJS) -o $@

test.o: test.cpp mm_debug_link_interface.h udp_log.h dprint.h
	$(MY_CPP) $(MY_CFLAGS) test.cpp
	
MOCK_OBJS := udp_log.o mmlink_server.o mmlink_connection.o mm_debug_link.o main.o
mock: $(MOCK_OBJS)
	$(MY_CPP) $(MY_LDFLAGS) $(MOCK_OBJS) -o $@

clean:
	rm -f mmlink test
	rm -f *.o *~ core .depend .*.cmd *.ko *.mod.c
	rm -f Module.markers Module.symvers modules.order
	rm -rf .tmp_versions Modules.symvers

