describe('visualizar una respuesta', () => {
  it('passes', () => {
    cy.visit('https://angular1-a60b9.web.app/question/62a669e6dabeeb180cf365d8')
    cy.contains("3 > 5 && 5< 3 ¿falso o verdadero?")
  })
})