describe('usuario no logueado', () => {
  it('passes', () => {
    cy.visit('https://angular1-a60b9.web.app/preguntas')
    cy.contains("Inicia sesión")
  })
})