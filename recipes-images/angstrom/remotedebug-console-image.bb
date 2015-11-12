require recipes-images/angstrom/gsrd-console-image.bb

IMAGE_INSTALL += " mmlink"

export IMAGE_BASENAME = "remotedebug-console-image"

