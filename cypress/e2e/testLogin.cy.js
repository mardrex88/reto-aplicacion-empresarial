describe('registrarse', () => {
  it('passes', () => {
    cy.visit('https://angular1-a60b9.web.app/login')
    cy.contains("Registrate")
  })
})