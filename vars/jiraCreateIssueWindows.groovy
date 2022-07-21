def call(Map config=[:]) {
  def rawBody = libraryResource 'com/planetpope/api/jira/createIssueJira.json'
  def binding = [
    key: "${config.key}",
    summary: "${config.summary}",
    description: "${config.description}",
    issueTypeName: "${config.issueTypeName}"
  ]
  def render = renderTemplate(rawBody,binding)
  // ahora tendra que executar un curl pero desde un powershell script

  bat('curl -D- -u %JIRA_CREDENTIALS% -X POST --data "'+render+'" -H "Content-Type: application/json" %JIRA_URL%/rest/api/2/issue')
}