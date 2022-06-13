describe('empty spec', () => {
  it('passes', () => {
    cy.visit('https://angular1-a60b9.web.app/preguntas')
    cy.contains('Todas las preguntas')
  })
})