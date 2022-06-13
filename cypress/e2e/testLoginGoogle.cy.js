describe('Login Google', () => {
  it('passes', () => {
    cy.visit('https://angular1-a60b9.web.app/login')
    cy.contains("With google").click()
  })
})