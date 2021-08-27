# EmotionalDiary

# PR 룰
## P-N 룰 (https://blog.banksalad.com/tech/banksalad-code-review-culture/)
뱅크 샐러드의 P-N룰을 따라서 리뷰 코맨트를 남깁니다.
룰은 아래와 같습니다.

**P1**: 꼭 반영해주세요 (Request changes)
- 리뷰어는 PR의 내용이 서비스에 중대한 오류를 발생할 수 있는 가능성을 잠재하고 있는 등 중대한 코드 수정이 반드시 필요하다고 판단되는 경우, P1 태그를 통해 리뷰 요청자에게 수정을 요청합니다. 리뷰 요청자는 p1 태그에 대해 리뷰어의 요청을 반영하거나, 반영할 수 없는 합리적인 의견을 통해 리뷰어를 설득할 수 있어야 합니다.

**P2**: 적극적으로 고려해주세요 (Request changes)
- 작성자는 P2에 대해 수용하거나 만약 수용할 수 없는 상황이라면 적합한 의견을 들어 토론할 것을 권장합니다.

**P3**: 웬만하면 반영해 주세요 (Comment)
- 작성자는 P3에 대해 수용하거나 만약 수용할 수 없는 상황이라면 반영할 수 없는 이유를 들어 설명하거나 다음에 반영할 계획을 명시적으로(JIRA 티켓 등으로) 표현할 것을 권장합니다. Request changes 가 아닌 Comment 와 함께 사용됩니다.

**P4**: 반영해도 좋고 넘어가도 좋습니다 (Approve)
- 작성자는 P4에 대해서는 아무런 의견을 달지 않고 무시해도 괜찮습니다. 해당 의견을 반영하는 게 좋을지 고민해 보는 정도면 충분합니다.

**P5**: 그냥 사소한 의견입니다 (Approve)
- 작성자는 P5에 대해 아무런 의견을 달지 않고 무시해도 괜찮습니다.

## PR 타이틀
- PR 타이틀은 EMOTION-${이슈번호} 으로 시작합니다.

# 코드 스타일
ktlint를 적용 중 입니다. 아래 명령어 실행 후 안드로이드 스튜디오를 재시작 해주세요.
```
./gradlew ktlintApplyToIdea
```

린트 룰에 위배되는 코드의 경우 PR을 생성하면 린트 에러를 체크합니다. 커밋 전에 린트 에러가 있는지를 파악하고 싶다면 아래 명령어를 이용해 프리 커밋 훅을 설치하고 체크 합니다
```
./gradlew addKtlintCheckGitPreCommitHook
```
위의 린트가 커밋룰이 적용되지 않는다면 아래와 같이 핀터레스트의 [ktlint Rule](https://github.com/pinterest/ktlint) 적용 후 다시 적용해야 합니다.
```
ktlint installGitPreCommitHook
```
적용 이후에 다시 핀터레스트의 githook이 Commit할때 정상 동작하는지 확인 하고 아래 명령어를 통해 pre-commit hook을 지우고 기존 적용하려는 룰을 재설치 해봅니다
```
rm .git/hooks/pre-commit
./gradlew addKtlintCheckGitPreCommitHook
```

# branch
- issue branch는 feature/emotion-${이슈번호} 의 규칙으로 생성합니다. 예) feature/emotion-34

# DESIGN
https://app.zeplin.io/project/60c23fafe6bd938adb07baa3

# [COMMIT MESSAGE HOOK](https://likeable-honeycrisp-8c3.notion.site/githook-commit-message-e53ae4d44d134c42be38428e8a6f181b)

- .git/hooks/commit-msg 위치에 아래 내용으로 파일추가
- mac에서 사용할려면 실행권한도 줘야 한다.

```
#!/bin/sh
commit_message=$(cat $1)
issue_no=$(git branch --show-current | sed -r "s/.*[-_#]([0-9]+).*/\1/")
echo "emotion-$issue_no : $commit_message" > $1
exit 0
```

### 피그마 : https://www.figma.com/file/tw4l10B4stA0nt7M51VtEq/%EA%B0%90%EC%A0%95%EB%8B%A4%EC%9D%B4%EC%96%B4%EB%A6%AC?node-id=0%3A1

