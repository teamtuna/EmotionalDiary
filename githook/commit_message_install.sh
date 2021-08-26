#!/bin/bash

GIT_FOLDER=../.git
if [ -d "${GIT_FOLDER}" ]; then
    echo "git location : ${GIT_FOLDER}"
else
    echo "has not git folder"
    exit 1
fi

HOOKS_FOLDER="$GIT_FOLDER/hooks"
if [ -d "${HOOKS_FOLDER}" ]; then
    echo "git hooks location : ${HOOKS_FOLDER}"
else
    echo "create git hooks folder : ${HOOKS_FOLDER}"
    mkdir "${HOOKS_FOLDER}"
fi

COMMIT_MSG_FILE="${HOOKS_FOLDER}/commit-msg"
if [ -f "${COMMIT_MSG_FILE}" ]; then
    TIME_SEED=$(date +%s)
    mv $COMMIT_MSG_FILE "$COMMIT_MSG_FILE.old.$TIME_SEED"
    echo "$COMMIT_MSG_FILE $COMMIT_MSG_FILE.old.$TIME_SEED"
fi

PREFIX_MESSAGE=emotion-

echo '#!/bin/bash' > $COMMIT_MSG_FILE
echo 'commit_message=$(cat $1)' >> $COMMIT_MSG_FILE
echo 'issue_no=$(git branch --show-current | sed -r "s/.*[-_#]([0-9]+).*/\1/")' >> $COMMIT_MSG_FILE
echo 'echo "$PREFIX_MESSAGE$issue_no : $commit_message" > $1' >> $COMMIT_MSG_FILE
echo 'exit 0' >> $COMMIT_MSG_FILE
chmod +x $COMMIT_MSG_FILE
