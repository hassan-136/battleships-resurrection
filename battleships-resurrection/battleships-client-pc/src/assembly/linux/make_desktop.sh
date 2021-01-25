#!/bin/bash

# Script just generates free desktop descriptor to start application

APP_HOME="$(realpath $(dirname ${BASH_SOURCE[0]}))"
TARGET=$APP_HOME/battleships-resurrection.desktop

echo [Desktop Entry] > $TARGET
echo Version=1.1 >> $TARGET
echo Encoding=UTF-8 >> $TARGET
echo Type=Application >> $TARGET
echo Name=Battleships >> $TARGET
echo GenericName=BattleShip >> $TARGET
echo Icon=$APP_HOME/logo.svg >> $TARGET
echo Exec=\"$APP_HOME/run.sh\" %f >> $TARGET
echo Comment=RuSoft BattleShip game >> $TARGET
echo "Categories=Game;" >> $TARGET
echo "Keywords=battleships;game;" >> $TARGET
echo Terminal=false >> $TARGET
echo StartupWMClass=battleships-resurrection-pc >> $TARGET

echo Desktop script has been generated: $TARGET

if [ -d ~/.gnome/apps ]; then
    echo copy to ~/.gnome/apps
    cp -f $TARGET ~/.gnome/apps
fi

if [ -d ~/.local/share/applications ]; then
    echo copy to ~/.local/share/applications
    cp -f $TARGET ~/.local/share/applications
fi

if [ -d ~/Desktop ]; then
    echo copy to ~/Desktop
    cp -f $TARGET ~/Desktop
fi

