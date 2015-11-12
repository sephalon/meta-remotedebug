DESCRIPTION = "mmlink application"
AUTHOR = "Aaron Ferrucci <aferrucc@altera.com>"
SECTION = "gsrd"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"
PR = "r0"
FILES_${PN} = "	/home/root/altera/* "

DEPENDS += "linux-altera-ltsi"		

SRC_URI = "file://dprint.h "
SRC_URI += "file://main.cpp "
SRC_URI += "file://Makefile "
SRC_URI += "file://mm_debug_link_interface.h "
SRC_URI += "file://mm_debug_link_linux.cpp "
SRC_URI += "file://mm_debug_link_linux.h "
SRC_URI += "file://mm_debug_link_mock.cpp "
SRC_URI += "file://mm_debug_link_mock.h "
SRC_URI += "file://mmlink_connection.cpp "
SRC_URI += "file://mmlink_connection.h "
SRC_URI += "file://mmlink_server.cpp "
SRC_URI += "file://mmlink_server.h "
SRC_URI += "file://test.cpp "
SRC_URI += "file://udp_log.cpp "
SRC_URI += "file://udp_log.h "
SRC_URI += "file://mm-debug-link.h "

S = "${WORKDIR}"

do_compile() {
	oe_runmake all
}

do_install() {
	install -d ${D}/home/root/altera
	install -m 0755 mmlink ${D}/home/root/altera/mmlink	
}

