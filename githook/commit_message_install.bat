@echo off

set GIT_FOLDER=..\.git
if exist %GIT_FOLDER% (
    echo [32mgit location : %GIT_FOLDER% [0m
) else (
    echo [31mhas not git folder [0m
    pause
    exit 1
)

set HOOKS_FOLDER=%GIT_FOLDER%\hooks
if exist %HOOKS_FOLDER% (
    echo [32mgit hooks location : %HOOKS_FOLDER% [0m
) else (
    echo [31mcreate git hooks folder : %HOOKS_FOLDER% [0m
    mkdir %HOOKS_FOLDER%
)

set COMMIT_MSG_FILE=%HOOKS_FOLDER%\commit-msg
if exist %COMMIT_MSG_FILE% (
    set TIME_SEED=%date:-=%_%time::=%
    move %COMMIT_MSG_FILE% %COMMIT_MSG_FILE%.old.%TIME_SEED%
    echo [31mmove %COMMIT_MSG_FILE% %COMMIT_MSG_FILE%.old.%TIME_SEED%[0m
)

set PREFIX_MESSAGE=emotion-

echo #!/bin/sh > %COMMIT_MSG_FILE%
echo commit_message=$(cat $1) >> %COMMIT_MSG_FILE%
echo issue_no=$(git branch --show-current ^| sed -r "1s/^.*[-_#]([0-9]+).*$/\1/") >> %COMMIT_MSG_FILE%
echo echo "%PREFIX_MESSAGE%$issue_no : $commit_message" ^> $1 >> %COMMIT_MSG_FILE%
echo exit 0 >> %COMMIT_MSG_FILE%

GOTO END
:END
