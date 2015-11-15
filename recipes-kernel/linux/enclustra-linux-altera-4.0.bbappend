FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://linux-socfpga-sld-hub.patch;patch=1"

do_patch_config() {
	sed -i "s|^.*CONFIG_ALTERA_MMDEBUGLINK[ =].*$|CONFIG_ALTERA_MMDEBUGLINK=y|g" .config
}

addtask do_patch_config before kernel_do_compile after do_configure
