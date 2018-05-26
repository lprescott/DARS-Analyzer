import os
import re

# traverse root directory, and list jar file names without .jar
for root, dirs, files in os.walk("."):
    path = root.split(os.sep)
    for file in files:
        print(re.sub('\.jar$', '', file))
