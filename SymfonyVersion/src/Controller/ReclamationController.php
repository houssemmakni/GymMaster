<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\ReclamationType;
use App\Form\ReclamationFrontType;
use App\Repository\ReclamationRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


#[Route('/reclamation')]
class ReclamationController extends AbstractController
{

    
    #[Route('/table', name: 'app_reclamation_table', methods: ['GET'])]
    public function table(ReclamationRepository $reclamationRepository): Response
    {
        return $this->render('reclamation/table.html.twig', [
            'reclamations' => $reclamationRepository->findAll(),
        ]);
    }


    #[Route('/', name: 'app_reclamation_index', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $reclamation = new Reclamation();
        $reclamation->setDateReclamation(new \DateTimeImmutable());
        $form = $this->createForm(ReclamationFrontType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($reclamation);
            $entityManager->flush();

            return $this->redirectToRoute('app_home', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('reclamation/new.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_reclamation_show', methods: ['GET'])]
    public function show(Reclamation $reclamation): Response
    {
        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_reclamation_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Reclamation $reclamation, EntityManagerInterface $entityManager, \Swift_Mailer $mailer): Response
    {
        $reclamation->setDateReponse(new \DateTimeImmutable());
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            // Check if the "Send Email" button was clicked
            if ($form->getClickedButton() && 'sendEmail' === $form->getClickedButton()->getName()) {
                // Access the user email from the form data
                $userEmail = $form->get('userEmail')->getData();
    
                // Send email logic
                $message = (new \Swift_Message('Reclamation Updated'))
                    ->setFrom('timekiller147@gmail.com')
                    ->setTo($userEmail)
                    ->setBody('Your reclamation has been updated.');
    
                $mailer->send($message);
            }
    
            $entityManager->flush();
    
            return $this->redirectToRoute('app_reclamation_table', [], Response::HTTP_SEE_OTHER);
        }
    
        return $this->renderForm('reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form,
        ]);
}
    #[Route('/{id}', name: 'app_reclamation_delete', methods: ['POST'])]
    public function delete(Request $request, Reclamation $reclamation, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getId(), $request->request->get('_token'))) {
            $entityManager->remove($reclamation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_reclamation_index', [], Response::HTTP_SEE_OTHER);
    }
}
